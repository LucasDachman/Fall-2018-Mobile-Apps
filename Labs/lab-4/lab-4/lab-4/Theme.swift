//
//  Theme.swift
//  lab-4
//
//  Created by Lucas Dachman on 10/12/18.
//  Copyright © 2018 Lucas Dachman. All rights reserved.
//

import Foundation
import UIKit

class Theme: Codable {
    // constants
    let defaultSize = 14
    
    // variables
    var bgColor: String? = "white"
    var text: String? = "Some Text Here"
    var textColor: String? = "black"
    var textSize: Int? = 14
    
    init() {
        
    }

    init(backGroundColor: String?, text: String?, textColor: String?, textSize: Int?) {
        self.bgColor = backGroundColor
        self.text = text
        self.textColor = textColor
        self.textSize = textSize
    }
}
