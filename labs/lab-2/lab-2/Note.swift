//
//  Note.swift
//  lab-2
//
//  Created by Lucas Dachman on 2/18/19.
//  Copyright © 2019 Lucas Dachman. All rights reserved.
//

import Foundation

struct Note : Codable {
    init(_ text: String) {
        self.text = text
        self.uuid = UUID()
    }
    
    var text: String
    var uuid: UUID
}
