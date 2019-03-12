//
//  EditTableViewController.swift
//  project-1
//
//  Created by Lucas Dachman on 3/8/19.
//  Copyright © 2019 Lucas Dachman. All rights reserved.
//

import UIKit

class EditTableViewController: UITableViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        
    }
    
    @IBAction func onTouchDone(_ sender: Any) {
        dismiss(animated: true, completion: nil)
    }
}
