//
//  FirstViewController.swift
//  cirkit
//
//  Created by Lucas Dachman on 10/15/18.
//  Copyright © 2018 Lucas Dachman. All rights reserved.
//

import UIKit

class SeriesViewController: UIViewController, UITextFieldDelegate {
    @IBOutlet weak var r1Field: UITextField!
    @IBOutlet weak var r2Field: UITextField!
    @IBOutlet weak var r1Seg: UISegmentedControl!
    @IBOutlet weak var r2Seg: UISegmentedControl!
    @IBOutlet weak var resultLabel: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        r1Field.delegate = self
        r2Field.delegate = self
        calculate()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func calculate() {
        resultLabel.text = resistorUtil.calculate(r1Field: r1Field, r2Field: r2Field, r1Seg: r1Seg, r2Seg: r2Seg, type: "Series")
    }
    
    /*
    func calculate() {
        
        adjustForDecimal(r1Field)
        adjustForDecimal(r2Field)
        // ensure that both text fields have values
        if let r1 = Float(r1Field.text!), let r2 = Float(r2Field.text!) {
            if r1 >= 0 && r2 >= 0 {
                let mult1 = getMultiplier(r1Seg.titleForSegment(at: r1Seg.selectedSegmentIndex))
                let mult2 = getMultiplier(r2Seg.titleForSegment(at: r2Seg.selectedSegmentIndex))
                var result:Float = (r1 * mult1) + (r2 * mult2)
                if result >= 100000 {
                    result /= 100000
                    resultLabel.text = String(result) + "MΩ"
                }
                else if result >= 1000 {
                    result /= 1000
                    resultLabel.text = String(result) + "KΩ"
                }
                else {
                    resultLabel.text = String(result) + "Ω"
                }
            } else {
                resultLabel.text = "error"
            }
            
        } else {
            resultLabel.text = "–"
        }
        
    }
 */
    
    @IBAction func editingChanged(_ sender: Any) {
        calculate()
    }
    @IBAction func segValChanged(_ sender: Any) {
        calculate()
    }
    
    override func touchesBegan(_ touches: Set<UITouch>,
                               with event: UIEvent?) {
        self.view.endEditing(true)
        calculate()
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        r1Field.resignFirstResponder()
        r2Field.resignFirstResponder()
        calculate()
        return true
    }

    
}

