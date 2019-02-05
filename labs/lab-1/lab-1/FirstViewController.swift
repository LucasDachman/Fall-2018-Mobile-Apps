//
//  FirstViewController.swift
//  lab-1
//
//  Created by Lucas Dachman on 2/4/19.
//  Copyright © 2019 Lucas Dachman. All rights reserved.
//

import UIKit
import os

class FirstViewController: UIViewController, UIPickerViewDelegate, UIPickerViewDataSource {
    
    @IBOutlet weak var soundPicker: UIPickerView!
    
    var categories: [String] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        loadData()
        soundPicker.delegate = self
        soundPicker.dataSource = self
    }
    
    func getSounds() {
        let urls = Bundle.main.urls(forResourcesWithExtension: "wav", subdirectory: "Drum_Samples/Claps")
        let stringUrls = urls.map{$0[3].lastPathComponent} ?? "none"
        os_log("URLS: %@", stringUrls)
    }
    
    func loadData() {
        if let pathURL = Bundle.main.url(forResource: "SoundCategories", withExtension: "plist") {
            let plistDecoder = PropertyListDecoder()
            do {
                let data = try Data(contentsOf: pathURL)
                categories = try plistDecoder.decode([String].self, from: data)
            } catch {
                print(error)
            }
        }
    }
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return categories.count
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return categories[row]
    }
    
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        
    }
}
