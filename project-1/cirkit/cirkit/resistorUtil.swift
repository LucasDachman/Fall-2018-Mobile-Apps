//
//  resistorUtil.swift
//  cirkit
//
//  Created by Lucas Dachman on 10/15/18.
//  Copyright © 2018 Lucas Dachman. All rights reserved.
//

import Foundation
import UIKit

class resistorUtil {
    static func adjustForDecimal(_ field: UITextField) {
        if let val = field.text {
            if val.hasPrefix(".") {
                field.text = "0" + field.text!
            }
        }
    }
    
    static func getMultiplier(_ text: String?) -> Float {
        if let option = text {
            switch option {
            case "Ω":
                return 1
            case "KΩ":
                return 1000
            case "MΩ":
                return 100000
            default:
                return 1
            }
        }
        return 1.0
    }
    
    static func calculate(r1Field:UITextField,r2Field:UITextField, r1Seg:UISegmentedControl, r2Seg:UISegmentedControl, type:String) -> String {
        
        resistorUtil.adjustForDecimal(r1Field)
        resistorUtil.adjustForDecimal(r2Field)
        var resultStr:String = ""
        // ensure that both text fields have values
        if let r1 = Float(r1Field.text!), let r2 = Float(r2Field.text!) {
            if r1 >= 0 && r2 >= 0 {
                let mult1 = resistorUtil.getMultiplier(r1Seg.titleForSegment(at: r1Seg.selectedSegmentIndex))
                let mult2 = resistorUtil.getMultiplier(r2Seg.titleForSegment(at: r2Seg.selectedSegmentIndex))
                var result:Float = 0
                
                if type == "Series" {
                    result = (r1 * mult1) + (r2 * mult2)
                }
                else if type == "Parallel" {
                    result = 1 / ((1 / (r1 * mult1)) + (1 / r2 * mult2))
                }
                
                if result >= 100000 {
                    result /= 100000
                    resultStr = String(result) + "MΩ"
                }
                else if result >= 1000 {
                    result /= 1000
                    resultStr = String(result) + "KΩ"
                }
                else {
                    resultStr = String(result) + "Ω"
                }
            } else {
                resultStr = "error"
            }
            
        } else {
            resultStr = "–"
        }
        return resultStr
    }
}

