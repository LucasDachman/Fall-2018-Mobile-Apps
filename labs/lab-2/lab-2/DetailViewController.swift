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
    var noteUUID: UUID?

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        textView.delegate = self
    }
    
    override func viewWillAppear(_ animated: Bool) {
        if let noteUUID = noteUUID {
            if let note = NotesStore.get(withUUID: noteUUID) {
                textView.text = note.text
            }
        }
    }
    
    func textViewDidChange(_ textView: UITextView) {
        if let noteUUID = noteUUID {
            if textView.text != NotesStore.get(withUUID: noteUUID)!.text {
                NotesStore.replace(noteWithUUID: noteUUID, with: textView.text)
            }
        }
    }
}
