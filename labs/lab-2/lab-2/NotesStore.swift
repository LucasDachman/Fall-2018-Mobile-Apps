//
//  NotesStore.swift
//  lab-2
//
//  Created by Lucas Dachman on 2/18/19.
//  Copyright © 2019 Lucas Dachman. All rights reserved.
//

import Foundation

class NotesStore {
    var notes = [String]()
    
    static var instance = NotesStore()
    
    static var notes: [String] {
        get {
            return instance.notes
        }
    }
    
    static func append(note: String) {
        instance.notes.append(note)
    }
    
    static func insert(_ note: String, at index: Int) {
        instance.notes.insert(note, at: index)
    }

    static func remove(at index: Int) {
        instance.notes.remove(at: index)
    }

    static func replace(at index: Int, with text: String) {
        instance.notes[index] = text
    }
}
