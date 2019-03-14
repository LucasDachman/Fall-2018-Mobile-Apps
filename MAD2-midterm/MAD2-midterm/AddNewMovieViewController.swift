//
//  AddNewMovieViewController.swift
//  MAD2-midterm
//
//  Created by Lucas Dachman on 3/14/19.
//  Copyright © 2019 Lucas Dachman. All rights reserved.
//

import UIKit

class AddNewMovieViewController: UIViewController {
    @IBOutlet weak var nameField: UITextField!
    @IBOutlet weak var urlField: UITextField!
    var newMovie: Movie?

    override func viewDidLoad() {
        super.viewDidLoad()
        newMovie = nil
    }
    
    @IBAction func onCancel(_ sender: Any) {
        dismiss(animated: true, completion: nil)
        newMovie = nil
    }
    
    @IBAction func onDone(_ sender: Any) {
        guard let name = nameField.text,
                let url = urlField.text,
                !name.isEmpty,
                !url.isEmpty else {
            dismiss(animated: true, completion: nil)
            return
        }
        print("onDone")
        MovieList.add(Movie(name: name, url: url))
        dismiss(animated: true, completion: nil)
    }
}
