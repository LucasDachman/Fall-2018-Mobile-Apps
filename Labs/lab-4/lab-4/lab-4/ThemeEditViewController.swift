//
//  ThemeEditViewController.swift
//  lab-4
//
//  Created by Lucas Dachman on 10/12/18.
//  Copyright © 2018 Lucas Dachman. All rights reserved.
//

import UIKit

class ThemeEditViewController: UIViewController, UIPickerViewDelegate, UIPickerViewDataSource {
    @IBOutlet weak var textView: UITextView!
    @IBOutlet weak var textColorPicker: UIPickerView!
    @IBOutlet weak var textSizePicker: UIPickerView!
    @IBOutlet weak var bgColorPicker: UIPickerView!
    
    var theme: Theme = Theme()
    
    let colors = [[(color: UIColor.red, name: "red"),
                   (color: UIColor.blue, name: "blue"),
                   (color: UIColor.green, name: "green"),
                   (color: UIColor.black, name: "black")]]
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return colors.count
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return colors[component].count
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return colors[component][row].name
    }
    
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        if pickerView == textColorPicker {
            self.theme.textColor = colors[component][row].color
        }
        else if pickerView == bgColorPicker {
            self.theme.bgColor = colors[component][row].color
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        textColorPicker.delegate = self
        textColorPicker.dataSource = self
        textSizePicker.delegate = self
        textSizePicker.dataSource = self
        bgColorPicker.delegate = self
        bgColorPicker.dataSource = self
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
