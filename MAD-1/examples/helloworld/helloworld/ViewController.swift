//
//  ViewController.swift
//  helloworld
//
//  Created by Lucas Dachman on 8/30/18.
//  Copyright © 2018 Lucas Dachman. All rights reserved.
//

import UIKit
import os.log

class ViewController: UIViewController {
    @IBOutlet weak var messageLabel: UILabel!
    @IBOutlet weak var showerSwitch: UISwitch!
    @IBOutlet weak var sliderComp: UISlider!
    
    
    @IBAction func sliderValueChanged(_ sender: Any) {
        let size = sliderComp.value
        messageLabel.font = messageLabel.font.withSize(CGFloat(size))
    }
    
    @IBAction func switchValueChanged(_ sender: Any) {
        if showerSwitch.isOn == true {
            messageLabel.text = "Hello, World!"
        }
        else {
            messageLabel.text = ""
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        sliderComp.minimumValue = 4
        sliderComp.maximumValue = 72
        messageLabel.adjustsFontSizeToFitWidth = true
        showerSwitch.isOn = false
        messageLabel.text = ""
        messageLabel.font = messageLabel.font.withSize(CGFloat(sliderComp.value))
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

