//
//  SecondViewController.swift
//  cirkit
//
//  Created by Lucas Dachman on 10/15/18.
//  Copyright © 2018 Lucas Dachman. All rights reserved.
//

import UIKit

class ParallelViewController: UIViewController, UITextFieldDelegate {
    @IBOutlet weak var resultLabel: UILabel!
    @IBOutlet weak var r1Field: UITextField!
    @IBOutlet weak var r2Field: UITextField!
    @IBOutlet weak var r1Seg: UISegmentedControl!
    @IBOutlet weak var r2Seg: UISegmentedControl!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        r1Field.delegate = self
        r2Field.delegate = self
        calculate()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func calculate() {
        resultLabel.text = resistorUtil.calculate(r1Field: r1Field, r2Field: r2Field, r1Seg: r1Seg, r2Seg: r2Seg, type:"Parallel")
    }

    @IBAction func editingChanged(_ sender: Any) {
        calculate()
    }
    
    @IBAction func segChanged(_ sender: Any) {
        calculate()
    }
    
    override func touchesBegan(_ touches: Set<UITouch>,
                               with event: UIEvent?) {
        self.view.endEditing(true)
        calculate()
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        r1Field.resignFirstResponder()
        r2Field.resignFirstResponder()
        calculate()
        return true
    }
}

