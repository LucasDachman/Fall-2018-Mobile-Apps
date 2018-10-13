//
//  ThemeEditViewController.swift
//  lab-4
//
//  Created by Lucas Dachman on 10/12/18.
//  Copyright © 2018 Lucas Dachman. All rights reserved.
//

import UIKit

class ThemeEditViewController: UIViewController, UIPickerViewDelegate, UIPickerViewDataSource, UITextFieldDelegate {
    @IBOutlet weak var textField: UITextField!
    @IBOutlet weak var textColorPicker: UIPickerView!
    @IBOutlet weak var textSizePicker: UIPickerView!
    @IBOutlet weak var bgColorPicker: UIPickerView!
    
    var theme: Theme = Theme()
    
    let colors = [["white","red","blue","green","black"]]
    let textSizes = [[14, 24, 48, 72]]
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // picker view stuff
        textColorPicker.delegate = self
        textColorPicker.dataSource = self
        textSizePicker.delegate = self
        textSizePicker.dataSource = self
        bgColorPicker.delegate = self
        bgColorPicker.dataSource = self
        
        // textfield stuff
        textField.delegate = self
        
        // setup textField
        textField.placeholder = "type text here..."
        setupThemeEditor()
    }
    
    func setupThemeEditor() {
        // set text color picker
        let textColorRow = colors[0].index(where: {$0 == theme.textColor})
        textColorPicker.selectRow(textColorRow!, inComponent: 0, animated: false)
        // set text size picker
        let textSizeRow = textSizes[0].index(where: {$0 == theme.textSize})
        textSizePicker.selectRow(textSizeRow!, inComponent: 0, animated: false)
        // set background color picker
        let bgColorRow = colors[0].index(where: {$0 == theme.bgColor})
        bgColorPicker.selectRow(bgColorRow!, inComponent: 0, animated: false)
        // set text
        textField.text = theme.text
        
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
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
            return colors[component][row]
        }
        else if pickerView == textSizePicker {
            return String(textSizes[component][row])
        }
        return ""
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
            theme.text = textField.text
            self.theme.textColor = colors[0][textColorPicker.selectedRow(inComponent: 0)]
            self.theme.textSize = textSizes[0][textSizePicker.selectedRow(inComponent: 0)]
            self.theme.bgColor = colors[0][bgColorPicker.selectedRow(inComponent: 0)]
            let mainView = segue.destination as! ViewController
            mainView.theme = self.theme
        }
    }

}
