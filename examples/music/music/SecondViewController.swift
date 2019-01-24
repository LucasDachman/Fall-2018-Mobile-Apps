//
//  SecondViewController.swift
//  music
//
//  Created by Lucas Dachman on 1/24/19.
//  Copyright © 2019 Lucas Dachman. All rights reserved.
//

import UIKit

class SecondViewController: UIViewController, UIPickerViewDelegate, UIPickerViewDataSource {
    
    @IBOutlet weak var musicPicker: UIPickerView!
    @IBOutlet weak var musicLabel: UILabel!
    
    let genre = ["Country", "Pop", "Rock", "Classical", "Alternative", "Hip Hop", "Jazz"]
    let decade = ["1950s", "1960s", "1970s", "1980s", "1990s", "2000s", "2010s"]
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        musicPicker.delegate = self
        musicPicker.dataSource = self
        musicLabel.text = ""
    }
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 2
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        switch component {
        case 0:
            return genre.count
        case 1:
            return decade.count
        default:
            return 0
        }
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        switch component {
        case 0:
            return genre[row]
        case 1:
            return decade[row]
        default:
            return "oops"
        }
    }
    
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        let genreChoice = genre[pickerView.selectedRow(inComponent: 0)]
        let decadeChoice = decade[pickerView.selectedRow(inComponent: 1)]
        musicLabel.text = "\(decadeChoice) \(genreChoice) is awesome!"
    }
}
