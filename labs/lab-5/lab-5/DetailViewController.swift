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
    var noteID: String?

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        textView.delegate = self
    }
    
    override func viewWillAppear(_ animated: Bool) {
        if let noteID = noteID {
            if let note = NotesStore.get(withID: noteID) {
                textView.text = note.text
            }
        }
    }
    
    func textViewDidChange(_ textView: UITextView) {
        if let noteID = noteID {
            if textView.text != NotesStore.get(withID: noteID)!.text {
                NotesStore.replace(noteWithID: noteID, with: textView.text)
            }
        }
    }
}
