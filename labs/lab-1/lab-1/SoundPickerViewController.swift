//
//  FirstViewController.swift
//  lab-1
//
//  Created by Lucas Dachman on 2/4/19.
//  Copyright © 2019 Lucas Dachman. All rights reserved.
//

import UIKit
import os

class SoundPickerViewController: UIViewController, UIPickerViewDelegate, UIPickerViewDataSource {
    
    @IBOutlet weak var soundPicker: UIPickerView!
    
    var categories: [String] = []
    let NUM_TRACKS = 4
    enum PickerComponent: Int {
        case TRACK, CATEGORY, SOUND
    }
    
    var currentTrack = 0, currentSound = 0, currentCategory = 0
    var currentSoundNames: [String] = []
    var trackConfigs = [TrackConfig]()
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        loadData()
        soundPicker.delegate = self
        soundPicker.dataSource = self
        
        // initialize track configs
        for _ in 0..<NUM_TRACKS {
            trackConfigs.append(TrackConfig())
        }
    }
    
    override func viewDidAppear(_ animated: Bool) {
        soundPicker.selectRow(0, inComponent: PickerComponent.TRACK.rawValue, animated: true)
        soundPicker.selectRow(0, inComponent: PickerComponent.CATEGORY.rawValue, animated: true)
        soundPicker.selectRow(0, inComponent: PickerComponent.SOUND.rawValue, animated: true)
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
            let config = trackConfigs[currentTrack]
            if currentCategory != config.category {
                currentCategory = config.category
                currentSoundNames = getSoundNames(forCategory: categories[currentCategory])
            }
            currentSound = config.sound
            soundPicker.selectRow(config.category, inComponent: PickerComponent.CATEGORY.rawValue, animated: true)
            soundPicker.selectRow(config.sound, inComponent: PickerComponent.SOUND.rawValue, animated: true)
            soundPicker.reloadComponent(PickerComponent.SOUND.rawValue)
        case PickerComponent.CATEGORY.rawValue:
            currentCategory = row
            trackConfigs[currentTrack].category = currentCategory
            trackConfigs[currentTrack].sound = currentSound
            currentSoundNames = getSoundNames(forCategory: categories[currentCategory])
            soundPicker.selectRow(0, inComponent: PickerComponent.SOUND.rawValue, animated: true)
            soundPicker.reloadComponent(PickerComponent.SOUND.rawValue)
        case PickerComponent.SOUND.rawValue:
            currentSound = row
            trackConfigs[currentTrack].category = currentCategory
            trackConfigs[currentTrack].sound = currentSound
        default:
            print("no component found: \(row)")
        }
        setState()
    }
    
    func setState() {
        var soundNames = [String]()
        for config in trackConfigs {
            let category = categories[config.category]
            var soundName = getSoundNames(forCategory: category)[config.sound]
            soundName.removeLast(4)
            soundName = "Drum_Samples/\(category)/\(soundName)"
            soundNames.append(soundName)
        }
        ChosenSounds.setSounds(soundNames)
    }
}

