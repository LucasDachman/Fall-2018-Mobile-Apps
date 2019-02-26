//
//  Character.swift
//  HarryPotter
//
//  Created by Lucas Dachman on 2/19/19.
//  Copyright © 2019 Lucas Dachman. All rights reserved.
//

import Foundation

class Character : Codable {
    let name : String
    let url: String
}

class CharacterDataModelController {
    var characters = [Character]()
    let fileName = "harrypotter2"
    
    func loadData(){
        if let pathURL = Bundle.main.url(forResource: fileName,
                                         withExtension: "plist"){
            //creates a property list decoder object
            let plistdecoder = PropertyListDecoder()
            do {
                let data = try Data(contentsOf: pathURL)
                //decodes the property list
                characters = try plistdecoder.decode([Character].self, from:
                    data)
            } catch {
                // handle error
                print(error)
            }
        }
    }
}
