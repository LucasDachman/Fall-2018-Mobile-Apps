//
//  ViewController.swift
//  week-5
//
//  Created by Lucas Dachman on 9/25/18.
//  Copyright © 2018 Lucas Dachman. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UITextFieldDelegate {
    
    @IBOutlet weak var checkAmount: UITextField!
    @IBOutlet weak var tipPercent: UITextField!
    @IBOutlet weak var numPeople: UITextField!
    @IBOutlet weak var tipDue: UILabel!
    @IBOutlet weak var totalDue: UILabel!
    @IBOutlet weak var perPersonDue: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        checkAmount.delegate = self
        tipPercent.delegate = self
        numPeople.delegate = self
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
    
    func textFieldDidEndEditing(_ textField: UITextField) {
        updateTipTotals()
    }
    
    func updateTipTotals() {
        var amount: Float
        var pct: Float
        
        let currencyFormatter = NumberFormatter()
        currencyFormatter.numberStyle = .currency
        
        if checkAmount.text!.isEmpty {
            amount = 0.0
        } else {
            amount = Float(checkAmount.text!)!
        }
        
        if tipPercent.text!.isEmpty {
            pct = 0.0
        } else {
            pct = Float(tipPercent.text!)! / 100.0
        }
        
        let tipAmount = amount * pct
        let total = amount + tipAmount
        var numP: Float = 0.0
        if !numPeople.text!.isEmpty {
            numP = Float(numPeople.text!)!
        }
        if numP == 0.0 {
            perPersonDue.text = nil
            let alert = UIAlertController(title: "warning", message: "Number of people must be greater than 0", preferredStyle: .alert)
            alert.addAction(UIAlertAction(title: "Cancel", style: .default, handler: nil))
            alert.addAction(UIAlertAction(title: "ok", style: .default, handler: { (action) in
                self.numPeople.text = "1"
                self.updateTipTotals()
            }))
            self.present(alert, animated: true, completion: nil)
        } else {
            perPersonDue.text = currencyFormatter.string(from: NSNumber(value: total / numP))
        }
        
        tipDue.text = currencyFormatter.string(from: NSNumber(value: tipAmount))
        totalDue.text = currencyFormatter.string(from: NSNumber(value: total))
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

