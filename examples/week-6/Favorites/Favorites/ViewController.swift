//
//  ViewController.swift
//  Favorites
//
//  Created by Lucas Dachman on 10/2/18.
//  Copyright © 2018 Lucas Dachman. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    @IBOutlet weak var bookLabel: UILabel!
    @IBOutlet weak var authorLabel: UILabel!
    var user = Favorite()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    @IBAction func unwindSegue(_ segue: UIStoryboardSegue) {
        bookLabel.text = user.favBook
        authorLabel.text = user.favAuthor
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

