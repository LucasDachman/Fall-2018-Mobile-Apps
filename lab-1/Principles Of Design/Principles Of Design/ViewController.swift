//
//  ViewController.swift
//  Principles Of Design
//
//  Created by Lucas Dachman on 9/11/18.
//  Copyright © 2018 Lucas Dachman. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    
    let elements = [
        ["title": "Color",
         "description": "some stuff about color",
         "image": "color"
        ],
        ["title": "Diagram",
         "description": "some stuff about diagram",
         "image": "diagram"
        ],
    ]
    
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var exampleImage: UIImageView!
    @IBOutlet weak var descriptionLabel: UILabel!
    @IBOutlet weak var prevButton: UIButton!
    @IBOutlet weak var nextButton: UIButton!
    
    var index = 0

    override func viewDidLoad() {
        super.viewDidLoad()
        setViewContent()
    }

    @IBAction func onPressNav(_ sender: UIButton) {
        if sender.tag == 1 {
            // go to prev page
            index = index - 1
            
        }
        else if sender.tag == 2 {
            // go to next page
            index = index + 1
        }
        
        setViewContent()
    }
    
    func setViewContent() {
        // set content
        let el = elements[index]
        titleLabel.text = el["title"]
        descriptionLabel.text = el["description"]
        exampleImage.image = UIImage(named: el["image"]!)
        
        // set buttons enabled/disabled
        if index == 0 {
            prevButton.isEnabled = false
        } else {
            prevButton.isEnabled = true
        }
        
        if index == elements.count - 1 {
            nextButton.isEnabled = false
        } else {
            nextButton.isEnabled = true
        }
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
}

