//
//  ChosenSounds.swift
//  lab-1
//
//  Created by Lucas Dachman on 2/4/19.
//  Copyright © 2019 Lucas Dachman. All rights reserved.
//

import Foundation

class ChosenSounds {
    var sounds = [String]()
    static let instance = ChosenSounds()
    
    static func getInstance() -> ChosenSounds{
        return instance
    }
    
    static func setSounds(_ sounds:[String]) {
        getInstance().sounds = sounds
    }
    
    static func getSounds() -> [String]{
        return getInstance().sounds
    }
}
