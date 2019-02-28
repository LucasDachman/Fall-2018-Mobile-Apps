//
//  AddViewController.swift
//  Recipes
//
//  Created by Lucas Dachman on 2/28/19.
//  Copyright © 2019 Lucas Dachman. All rights reserved.
//

import UIKit

class AddViewController: UIViewController {
    @IBOutlet weak var nameField: UITextField!
    @IBOutlet weak var urlField: UITextField!
    var newRecipe: Recipe?
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if let vc = segue.destination as? TableViewController,
            let name = nameField.text, let url = urlField.text {
                if !name.isEmpty && !url.isEmpty {
                    vc.recipeData.addRecipe(named: name, withURL: url)
                }
        }
    }

}
