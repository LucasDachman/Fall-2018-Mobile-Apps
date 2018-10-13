//
//  ThemeEditViewController.swift
//  lab-4
//
//  Created by Lucas Dachman on 10/12/18.
//  Copyright © 2018 Lucas Dachman. All rights reserved.
//

import UIKit

class ThemeEditViewController: UIViewController, UIPickerViewDelegate, UIPickerViewDataSource, UITextViewDelegate {
    @IBOutlet weak var textView: UITextView!
    @IBOutlet weak var textColorPicker: UIPickerView!
    @IBOutlet weak var textSizePicker: UIPickerView!
    @IBOutlet weak var bgColorPicker: UIPickerView!
    
    var theme: Theme = Theme()
    
    let colors = [[(color: UIColor.red, name: "red"),
                   (color: UIColor.blue, name: "blue"),
                   (color: UIColor.green, name: "green"),
                   (color: UIColor.black, name: "black")]]
    let textSizes = [[8, 12, 18, 28, 48]]
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // picker view stuff
        textColorPicker.delegate = self
        textColorPicker.dataSource = self
        textSizePicker.delegate = self
        textSizePicker.dataSource = self
        bgColorPicker.delegate = self
        bgColorPicker.dataSource = self
        
        // textview stuff
        // TODO: make keyboard close automatically
        textView.delegate = self
    }
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        if pickerView == textColorPicker || pickerView == bgColorPicker {
          return colors.count
        }
        else if pickerView == textSizePicker {
            return textSizes.count
        }
        return 0
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        if pickerView == textColorPicker || pickerView == bgColorPicker {
            return colors[component].count
        }
        else if pickerView == textSizePicker {
            return textSizes[component].count
        }
        return 0
        
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        if pickerView == textColorPicker || pickerView == bgColorPicker {
            return colors[component][row].name
        }
        else if pickerView == textSizePicker {
            return String(textSizes[component][row])
        }
        return ""
    }
    
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        if pickerView == textColorPicker {
            self.theme.textColor = colors[component][row].color
        }
        else if pickerView == bgColorPicker {
            self.theme.bgColor = colors[component][row].color
        }
        else if pickerView == textSizePicker {
            self.theme.textSize = textSizes[component][row]
        }
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    // MARK: - Navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
        if segue.identifier == "saveTheme" {
            let mainView = segue.destination as! ViewController
            mainView.theme = self.theme
        }
    }

}
