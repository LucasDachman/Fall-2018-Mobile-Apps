//
//  ViewController.swift
//  midterm
//
//  Created by Lucas Dachman on 10/18/18.
//  Copyright © 2018 Lucas Dachman. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UITextFieldDelegate {
    @IBOutlet weak var timeField: UITextField!
    @IBOutlet weak var weekSwitch: UISwitch!
    @IBOutlet weak var perWeekSlider: UISlider!
    @IBOutlet weak var milesLabel: UILabel!
    @IBOutlet weak var caloriesLabel: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        timeField.delegate = self
        calculate()
    }

    @IBAction func onWorkoutTouched(_ sender: UIButton) {
        calculate()
    }
    
    func calculate() {
        if let minutes = Float(timeField.text!) {
            // calculate miles
            let hours = (minutes / 60)
            milesLabel.text = String(hours * 6) + " miles"
            
            //calculate calories
            caloriesLabel.text = String(hours * 600) + " calories burned"
            
        } else {
            milesLabel.text = "–"
            caloriesLabel.text = "–"
        }
    }
    
    override func touchesBegan(_ touches: Set<UITouch>,
                               with event: UIEvent?) {
        self.view.endEditing(true)
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        timeField.resignFirstResponder()
        return true
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

