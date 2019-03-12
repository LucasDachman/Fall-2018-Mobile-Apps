//
//  EditTableViewController.swift
//  project-1
//
//  Created by Lucas Dachman on 3/8/19.
//  Copyright © 2019 Lucas Dachman. All rights reserved.
//

import UIKit

class EditTableViewController: UITableViewController {

    @IBOutlet weak var exposureSlider: UISlider!
    @IBOutlet weak var hueSlider: UISlider!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        exposureSlider.minimumValue = 0
        exposureSlider.maximumValue = 1
        exposureSlider.isContinuous = false
    }
    
    override func viewWillAppear(_ animated: Bool) {
        setSliders()
    }
    
    func setSliders() {
        exposureSlider.value = SliderConfig.exposure
        hueSlider.value = SliderConfig.hue
    }

    @IBAction func exposureSliderDidChange(_ sender: UISlider) {
        ImageEditor.exposureFilter.effectValue = sender.value
        SliderConfig.exposure = sender.value
    }
    
    @IBAction func hueSliderDidChange(_ sender: UISlider) {
        ImageEditor.hueFilter.effectValue = sender.value
        SliderConfig.hue = sender.value
    }
    
    
    @IBAction func onTouchDone(_ sender: Any) {
        dismiss(animated: true, completion: nil)
    }
}
