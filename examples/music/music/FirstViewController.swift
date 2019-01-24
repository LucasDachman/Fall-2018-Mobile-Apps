//
//  FirstViewController.swift
//  music
//
//  Created by Lucas Dachman on 1/24/19.
//  Copyright © 2019 Lucas Dachman. All rights reserved.
//

import UIKit

class FirstViewController: UIViewController, UIPickerViewDelegate, UIPickerViewDataSource {
    
    @IBOutlet weak var musicPicker: UIPickerView!
    @IBOutlet weak var musicLabel: UILabel!
    let genre = ["Country", "Pop", "Rock", "Classical", "Alternative", "Hip Hop", "Jazz"]
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        musicPicker.delegate = self
        musicPicker.dataSource = self
    }

    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return genre.count
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return genre[row]
    }
    
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        musicLabel.text = "\(genre[row]) is awesome!"
    }
}

