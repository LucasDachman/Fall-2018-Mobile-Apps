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
        ["title": "Texture",
         "description": "Texture can add tactility and depth. It can also make a design feel more tangible.",
         "image": "texture"
        ],
        ["title": "Time & Motion",
         "description": "Motion can bring life to a design. Blurring, waving or motion lines can help create this effect.",
         "image": "time-and-motion"
        ],
        ["title": "Figure And Ground",
         "description": "Negative space can create interesting and engaging designs.",
         "image": "figure-and-ground"
        ],
        ["title": "Framing",
         "description": "Framing helps to highlight, crop or decorate elements. Boxes and outlines can call atention to individual elements of a design.",
         "image": "framing"
        ],
        ["title": "Grid",
         "description": "The grid helps with aligning and arranging elements. Typically, a grid is implemented through an invisible system of rows and columns.",
         "image": "grid"
        ],
        ["title": "Hierarchy",
         "description": "Hierarchy helps a user navigate a design. Attributes like size and position can easily contribute to hierarchy.",
         "image": "hierarchy"
        ],
        ["title": "Layers",
         "description": "Layers help create multi-dimensional space. This can be achieved through stacking and opacity effects.",
         "image": "layers"
        ],
        ["title": "Modularity",
         "description": "Modularity invloves using shared constraints or characteristics across multiple designs or design elements.",
         "image": "modularity"
        ],
        ["title": "Pattern",
         "description": "Patterns invlove repetition and prediction. This can make a design fun and engaging.",
         "image": "pattern"
        ],
        ["title": "Point, Line & Plane",
         "description": "These elements can create emphasis and movement by directing the eye and highlighting specific parts of a design.",
         "image": "point-line-plane"
        ],
        ["title": "Rhythm & Balance",
         "description": "Rhythm and balance help to create space and hierarchy in a design. The key to achieving good balance is to consider the relationship between elements in a design.",
         "image": "rhythm-and-balance"
        ],
        ["title": "Rules & Randomness",
         "description": "Rules make a design ordered and predictable. Randomness can make a design more organic. Used together, rules and randomness can create engaging contrast.",
         "image": "rules-and-randomness"
        ],
        ["title": "Scale",
         "description": "Scale can draw attention to and from certain elements. This principle is essential to creating emphasis and hierarchy.",
         "image": "scale"
        ],
        ["title": "Transparency",
         "description": "Transparency helps elements interact on a page. This can also be used to create a sense of movement. This is also known as opacity.",
         "image": "transparency"
        ],
        ["title": "Color",
         "description": "A strong color pallete will create a strong design. Color can create moods and atmospheres.",
         "image": "color"
        ],
        ["title": "Diagram",
         "description": "Diagrams are a visual representation of data. They can simplify complex concepts.",
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

