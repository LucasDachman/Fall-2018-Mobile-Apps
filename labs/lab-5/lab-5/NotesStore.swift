//
//  NotesStore.swift
//  lab-2
//
//  Created by Lucas Dachman on 2/18/19.
//  Copyright © 2019 Lucas Dachman. All rights reserved.
//

import Foundation
import Firebase

class NotesStore {
    var notes = [Note]()
    var ref: DatabaseReference!
    var onDataUpdate: (() -> Void)?
    
    static var instance = NotesStore()
    static var filter = String()
    
    init() {
        setupFirebaseListener()
    }
    
    static var notes: [Note] {
        get {
            var val: [Note]
            if filter == "" {
                val = instance.notes
            } else {
                val = instance.notes.filter {$0.text.lowercased().contains(filter)}
            }
            print("getting notes. count is \(val.count)")
            return val
        }
    }
    
    static func get(withID id: String) -> Note? {
        return instance.notes.first(where: {$0.id == id})
    }
    
    static func append(note: String) {
        //create Dictionary
        let newNote = ["text": note]
        
        //create a new ID
        let reciperef = instance.ref.childByAutoId()
        
        //write data to the new ID in Firebase
        reciperef.setValue(newNote)
    }
    
//    static func insert(_ note: String, at index: Int) {
//        instance.notes.insert(Note(note), at: index)
//    }
    
    static func remove(withID id: String) {
        instance.ref.child(id).removeValue()
    }
    
    static func replace(noteWithID id: String, with text: String) {
        instance.ref.child(id).setValue(["text": text])
    }
    
    func setupFirebaseListener(){
        ref = Database.database().reference().child("notes")
        // set up a listener for Firebase data change events
        // this event will fire with the initial data and then all data changes
        ref.observe(DataEventType.value, with: {snapshot in
            self.notes.removeAll()
            //DataSnapshot represents the Firebase data at a given time
            for snap in snapshot.children.allObjects as! [DataSnapshot]{
                if let noteDict = snap.value as? [String: String],
                    let noteText = noteDict["text"]
                {
                    let newNote = Note(noteText, id: snap.key)
                    self.notes.append(newNote)
                }
            }
            self.onDataUpdate?()
        })
    }
}
