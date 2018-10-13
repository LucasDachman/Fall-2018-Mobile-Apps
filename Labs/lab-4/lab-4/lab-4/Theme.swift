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
    var bgColor: UIColor? = UIColor.white
    var text: String? = "Some Text Here"
    var textColor: UIColor? = UIColor.black
    var textSize: Int? = 14
    
    init() {
        
    }

    init(backGroundColor: UIColor?, text: String?, textColor: UIColor?, textSize: Int?) {
        self.bgColor = backGroundColor
        self.text = text
        self.textColor = textColor
        self.textSize = textSize
    }
}
