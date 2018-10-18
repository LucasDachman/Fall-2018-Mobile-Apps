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
    @IBOutlet weak var perWeekLabel: UILabel!
    @IBOutlet weak var milesLabel: UILabel!
    @IBOutlet weak var caloriesLabel: UILabel!
    @IBOutlet weak var typeSeg: UISegmentedControl!
    @IBOutlet weak var imageView: UIImageView!
    
    var milesMult: Float = 1;
    var caloriesMult: Float = 1;
    
    override func viewDidLoad() {
        super.viewDidLoad()
        timeField.delegate = self
        perWeekSlider.minimumValue = 1
        perWeekSlider.maximumValue = 14
        perWeekSlider.value = 5
        sliderChanged(perWeekSlider)
        typeSeg.selectedSegmentIndex = 0
        segChanged(typeSeg)
        calculate()
    }

    @IBAction func sliderChanged(_ sender: UISlider) {
        let value = Int(perWeekSlider.value)
        sender.setValue(Float(value), animated: true)
        perWeekLabel.text = String(value)
        calculate()
    }

    @IBAction func switchChanged(_ sender: UISwitch) {
        calculate()
    }
    
    @IBAction func segChanged(_ sender: Any) {
        switch typeSeg.selectedSegmentIndex {
        case 0:
            imageView.image = UIImage(named: "run")
            milesMult = 6
            caloriesMult = 600
        case 1:
            imageView.image = UIImage(named: "swim")
            milesMult = 2
            caloriesMult = 420
        case 2:
            imageView.image = UIImage(named: "bike")
            milesMult = 15
            caloriesMult = 510
        default:
            imageView.image = nil
            milesMult = 0
            caloriesMult = 0
        }
        calculate()
    }
    @IBAction func onWorkoutTouched(_ sender: UIButton) {
        calculate()
    }
    
    func calculate() {
        if let minutes = Float(timeField.text!) {
            if minutes < 30 {
                showAlert()
                return
            }
            
            // get workouts per week
            let perWeek:Float = perWeekSlider.value
            // get weekly multiplier
            let timeMult:Float = weekSwitch.isOn ? perWeek : 1
            
            // calculate miles
            let hours = (minutes / 60) * timeMult
            milesLabel.text = String(format: "%.2f", hours * milesMult) + " miles"
            
            //calculate calories
            caloriesLabel.text = String(format: "%.2f", hours * caloriesMult) + " calories burned"
            
        } else {
            milesLabel.text = "–"
            caloriesLabel.text = "–"
        }
    }
    
    func showAlert() {
        let alert = UIAlertController(title: "warning", message: "You need to work out more!", preferredStyle: .alert)
        alert.addAction(UIAlertAction(title: "Cancel", style: .default, handler: nil))
        alert.addAction(UIAlertAction(title: "Ok", style: .default, handler: { (action) in
            self.timeField.text = "30"
            self.calculate()
        }))
        self.present(alert, animated: true, completion: nil)
    }
    
    override func touchesBegan(_ touches: Set<UITouch>,
                               with event: UIEvent?) {
        self.view.endEditing(true)
        calculate()
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        timeField.resignFirstResponder()
        calculate()
        return true
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

