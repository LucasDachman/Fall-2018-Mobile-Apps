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
    let NUM_TRACKS = 4
    enum PickerComponent: Int {
        case TRACK, CATEGORY, SOUND
    }
    
    var currentTrack = 0, currentSound = 0, currentCategory = 0
    var currentSoundNames: [String] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        loadData()
        soundPicker.delegate = self
        soundPicker.dataSource = self
    }
    
    func getSoundNames(forCategory cat: String) -> [String] {
        // read names of files in given category directory
        guard let urls = Bundle.main.urls(forResourcesWithExtension: "wav", subdirectory: "Drum_Samples/\(cat)")
        else {
            print("cant find url for \(cat)")
            return []
        }
        let soundNames = urls.map{$0.lastPathComponent}
        return soundNames
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
        return 3
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        switch component {
        case PickerComponent.TRACK.rawValue:
            return NUM_TRACKS
        case PickerComponent.CATEGORY.rawValue:
            return categories.count
        case PickerComponent.SOUND.rawValue:
            return currentSoundNames.count
        default:
            return 0
        }
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        switch component {
        case PickerComponent.TRACK.rawValue:
            return "Track \(row + 1)"
        case PickerComponent.CATEGORY.rawValue:
            return categories[row]
        case PickerComponent.SOUND.rawValue:
            return currentSoundNames[row]
        default:
            return "Default"
        }
    }
    
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        switch component {
        case PickerComponent.TRACK.rawValue:
            currentTrack = row
        case PickerComponent.CATEGORY.rawValue:
            currentCategory = row
            currentSoundNames = getSoundNames(forCategory: categories[currentCategory])
            soundPicker.reloadComponent(PickerComponent.SOUND.rawValue)
        case PickerComponent.SOUND.rawValue:
            currentSound = row
        default:
            print("no component found: \(row)")
        }
    }
}
