//
//  ledViewController.swift
//  cirkit
//
//  Created by Lucas Dachman on 10/15/18.
//  Copyright © 2018 Lucas Dachman. All rights reserved.
//

import UIKit

class ledViewController: UIViewController, UITextFieldDelegate {
    @IBOutlet weak var vInField: UITextField!
    @IBOutlet weak var ledVField: UITextField!
    @IBOutlet weak var ledCurrentField: UITextField!
    @IBOutlet weak var resultLabel: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        vInField.delegate = self
        ledVField.delegate = self
        ledCurrentField.delegate = self
        calc()
    }

    @IBAction func onFieldEditingChanged(_ sender: Any) {
        calc()
    }
    
    func calc() {
        resistorUtil.adjustForDecimal(vInField)
        resistorUtil.adjustForDecimal(ledVField)
        resistorUtil.adjustForDecimal(ledCurrentField)
        if let vIn = Float(vInField.text!), let ledV = Float(ledVField.text!), let ledCurrent = Float(ledCurrentField.text!) {
            if ledV < 0 || ledCurrent < 0 || vIn < ledV {
                resultLabel.text = "error"
            } else {
                resultLabel.text = String(format: "%.2f", calcLED(sourceVoltage: vIn, ledVoltage: ledV, ledCurrent: ledCurrent)) + "Ω"
            }
        } else {
            resultLabel.text = "–"
        }
    }
    
    func calcLED(sourceVoltage:Float, ledVoltage:Float, ledCurrent:Float) -> Float {
        /**
         sourceVoltage is the source voltage, measured in volts (V),
         ledVoltage is the voltage drop across the LED, measured in volts (V),
         ledCurrent is the current through the LED, measured in milii-Amperes (mA)
         the return value is the resistance, measured in Ohms (Ω).
         **/
        return (sourceVoltage - ledVoltage) / ledCurrent
    }
    
    override func touchesBegan(_ touches: Set<UITouch>,
                               with event: UIEvent?) {
        self.view.endEditing(true)
        calc()
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        ledVField.resignFirstResponder()
        ledCurrentField.resignFirstResponder()
        vInField.resignFirstResponder()
        calc()
        return true
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
}
