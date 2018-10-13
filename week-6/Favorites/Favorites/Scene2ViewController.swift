//
//  Scene2ViewController.swift
//  Favorites
//
//  Created by Lucas Dachman on 10/2/18.
//  Copyright © 2018 Lucas Dachman. All rights reserved.
//

import UIKit

class Scene2ViewController: UIViewController, UITextFieldDelegate {
    @IBOutlet weak var bookField: UITextField!
    @IBOutlet weak var authorField: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        bookField.delegate = self
        authorField.delegate = self
        // Do any additional setup after loading the view.
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "doneFavs" {
            let scene1ViewController = segue.destination as! ViewController
            if bookField.text!.isEmpty == false {
                scene1ViewController.user.favBook = bookField.text
            }
            if authorField.text!.isEmpty == false {
                scene1ViewController.user.favAuthor = authorField.text
            }
        }
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
