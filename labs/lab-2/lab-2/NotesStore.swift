//
//  NotesStore.swift
//  lab-2
//
//  Created by Lucas Dachman on 2/18/19.
//  Copyright © 2019 Lucas Dachman. All rights reserved.
//

import Foundation

class NotesStore {
    var notes = [Note]()
    
    static var instance = NotesStore()
    static var filter = String()
    
    static var notes: [Note] {
        get {
            if filter == "" {
                return instance.notes
            }
            return instance.notes.filter {$0.text.contains(filter)}
        }
    }
    
    static func get(withUUID uuid: UUID) -> Note? {
        return instance.notes.first(where: {$0.uuid == uuid})
    }
    
    static func append(note: String) {
        instance.notes.append(Note(note))
    }
    
    static func insert(_ note: String, at index: Int) {
        instance.notes.insert(Note(note), at: index)
    }

    static func remove(withUUID uuid: UUID) {
        instance.notes.removeAll(where: {$0.uuid == uuid})
    }

    static func replace(noteWithUUID uuid: UUID, with text: String) {
        if let index = instance.notes.firstIndex(where: {$0.uuid == uuid}) {
            instance.notes[index].text = text
        }
        
    }
}
