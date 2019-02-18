//
//  DetailViewController.swift
//  lab-2
//
//  Created by Lucas Dachman on 2/18/19.
//  Copyright © 2019 Lucas Dachman. All rights reserved.
//

import UIKit

class DetailViewController: UIViewController, UITextViewDelegate {
    @IBOutlet weak var textView: UITextView!
    
    func configureView() {
        // Update the user interface for the detail item.
        if let noteIndex = noteIndex {
            if let textView = textView {
                textView.text = NotesStore.notes[noteIndex]
            }
        }
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        textView.delegate = self
        configureView()
    }

    var noteIndex: Int? {
        didSet {
            // Update the view.
            configureView()
        }
    }

    func textViewDidEndEditing(_ textView: UITextView) {
        // if the user changed the text, change it in the store

        if textView.text != NotesStore.notes[noteIndex!] {
            NotesStore.replace(at: noteIndex!, with: textView.text)
            print("replaced text!")

        }
    }
}

