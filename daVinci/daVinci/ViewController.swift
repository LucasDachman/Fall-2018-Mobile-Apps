//
//  ViewController.swift
//  daVinci
//
//  Created by Lucas Dachman on 9/4/18.
//  Copyright © 2018 Lucas Dachman. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    @IBOutlet weak var imageView: UIImageView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    @IBAction func selectArt(_ sender: UIButton) {
        if sender.tag == 1 {
            imageView.image = UIImage(named: "MonaLisa")
        }
        else if sender.tag == 2 {
            imageView.image = UIImage(named: "Vitruvian")
        }
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

