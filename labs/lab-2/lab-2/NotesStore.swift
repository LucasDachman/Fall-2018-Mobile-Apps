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
    var filename: String
    
    static var instance = NotesStore(withFile: "notes.plist")
    static var filter = String()
    
    init(withFile filename: String) {
        self.filename = filename
        loadData()
    }
    
    private func urlForFile(named filename: String) -> URL {
        let documentsUrl = FileManager.default.urls(for: .documentDirectory, in: .userDomainMask)[0]
        return documentsUrl.appendingPathComponent(filename)
    }
    
    func writeData(){
        let fileURL = urlForFile(named: filename)
        let plistencoder = PropertyListEncoder()
        plistencoder.outputFormat = .xml
        do {
            let encodedData = try plistencoder.encode(notes.self)
            try encodedData.write(to: fileURL)
        } catch {
            print(error)
        }
    }
    
    private func loadData() {
        let url = urlForFile(named: filename)
        
        // if file does not exist in documents, create id
        if !FileManager.default.fileExists(atPath: url.path) {
//            let bundleUrl = Bundle.main.url(forResource: filename, withExtension: "plist")
            if !FileManager.default.createFile(atPath: url.path, contents: Data(), attributes: nil) {
                print("can't create file")
            }
        }
        
        //creates a property list decoder object
        let plistdecoder = PropertyListDecoder()
        do {
            let encodedData = try Data(contentsOf: url)
            //decodes the property list
            notes = try plistdecoder.decode([Note].self, from: encodedData)
        } catch {
            print("can't decode file")
            print(error)
        }
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
