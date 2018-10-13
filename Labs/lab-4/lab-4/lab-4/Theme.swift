//
//  Theme.swift
//  lab-4
//
//  Created by Lucas Dachman on 10/12/18.
//  Copyright © 2018 Lucas Dachman. All rights reserved.
//

import Foundation
import UIKit

class Theme {
    var bgColor: UIColor?
    var text: String?
    var textColor: UIColor?
    var textSize: Int?
    
    init() {
        bgColor = UIColor.white
        text = "Hello"
        textColor = UIColor.black
        textSize = 14
    }
    
    init(backGroundColor: UIColor?, text: String?, textColor: UIColor?, textSize: Int?) {
        self.bgColor = backGroundColor
        self.text = text
        self.textColor = textColor
        self.textSize = textSize
    }
}
