//
//  AddCountryViewController.swift
//  countries
//
//  Created by Lucas Dachman on 2/7/19.
//  Copyright © 2019 Lucas Dachman. All rights reserved.
//

import UIKit

class AddCountryViewController: UIViewController {
    @IBOutlet weak var countryTextField: UITextField!
    var newCountryName = String()
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    

    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
        if segue.identifier == "done" {
            newCountryName = countryTextField.text ?? ""
        }
    }

}
