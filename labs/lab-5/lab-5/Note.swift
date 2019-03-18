//
//  Note.swift
//  lab-2
//
//  Created by Lucas Dachman on 2/18/19.
//  Copyright © 2019 Lucas Dachman. All rights reserved.
//

import Foundation

struct Note : Codable {
    init(_ text: String, id: String) {
        self.text = text
        self.id = id
    }
    
    var text: String
    var id: String
}
