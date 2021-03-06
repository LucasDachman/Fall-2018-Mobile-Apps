//
//  ViewController.swift
//  resistor-calculator
//
//  Created by Lucas Dachman on 9/27/18.
//  Copyright © 2018 Lucas Dachman. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UITextFieldDelegate {
    @IBOutlet weak var seg: UISegmentedControl!
    @IBOutlet weak var r1Text: UITextField!
    @IBOutlet weak var r2Text: UITextField!
    @IBOutlet weak var resultText: UILabel!
    @IBOutlet weak var r1UnitSeg: UISegmentedControl!
    @IBOutlet weak var r2UnitSeg: UISegmentedControl!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        r1Text.delegate = self
        r2Text.delegate = self
        resultText.text = ""
    }
    
    override func touchesBegan(_ touches: Set<UITouch>,
                               with event: UIEvent?) {
        self.view.endEditing(true)
        calculate()
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        r1Text.resignFirstResponder()
        r2Text.resignFirstResponder()
        calculate()
        return true
    }
    
    @IBAction func onValChange(_ sender: Any) {
        calculate()
    }
    
    @IBAction func onValChange2(_ sender: Any) {
        calculate()
    }
    
    @IBAction func onSegChange(_ sender: Any) {
        calculate()
    }
    
    func adjustForDecimal(_ field: UITextField) {
        if let val = field.text {
            if val.hasPrefix(".") {
                field.text = "0" + field.text!
            }
        }
    }
    
    func calculate() {
        
        
        adjustForDecimal(r1Text)
        adjustForDecimal(r2Text)
        // ensure that both text fields have values
        if let r1 = Float(r1Text.text!), let r2 = Float(r2Text.text!) {
            if r1 >= 0 && r2 >= 0 {
                let mult1 = getMultiplier(r1UnitSeg.titleForSegment(at: r1UnitSeg.selectedSegmentIndex))
                let mult2 = getMultiplier(r2UnitSeg.titleForSegment(at: r2UnitSeg.selectedSegmentIndex))
                let type = seg.titleForSegment(at: seg.selectedSegmentIndex)
                var result: Float = 0
                if type == "Series" {
                    result = (r1 * mult1) + (r2 * mult2)
                }
                else if type == "Parallel" {
                    result = 1 / ((1 / (r1 * mult1)) + (1 / r2 * mult2))
                }
                if result >= 100000 {
                    result /= 100000
                    resultText.text = String(result) + "MΩ"
                }
                else if result >= 1000 {
                    result /= 1000
                    resultText.text = String(result) + "KΩ"
                }
                else {
                    resultText.text = String(result) + "Ω"
                }
            } else {
                resultText.text = "error"
                showAlert()
            }
            
        } else {
            if let r1 = r1Text.text, let r2 = r2Text.text {
                if r1.count > 0 && r2.count > 0 {
                    showAlert()
                }
            }
            resultText.text = ""
        }
        
    }
    
    func getMultiplier(_ text: String?) -> Float {
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
    
    func showAlert() {
        let alert=UIAlertController(title: "Warning", message: "Resistor values must be non-negative rational numbers", preferredStyle: UIAlertControllerStyle.alert)
        let okAction=UIAlertAction(title: "OK", style: UIAlertActionStyle.default, handler: nil)
        alert.addAction(okAction)
        present(alert, animated: true, completion: nil)
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

