//
//  CollectionViewController.swift
//  lab-3
//
//  Created by Lucas Dachman on 2/25/19.
//  Copyright © 2019 Lucas Dachman. All rights reserved.
//

import UIKit

private let reuseIdentifier = "ImageCell"

class CollectionViewController: UICollectionViewController, UICollectionViewDelegateFlowLayout {
    
    var imageNames = [String]()

    override func viewDidLoad() {
        super.viewDidLoad()
        for i in 1...7 {
            imageNames.append("img\(i)")
        }
    }
    
    override func numberOfSections(in collectionView: UICollectionView) -> Int {
        return 1
    }
    
    override func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return imageNames.count
    }
    
    override func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: reuseIdentifier, for: indexPath) as! CollectionViewCell
        cell.imageView.image = UIImage(named: imageNames[indexPath.row])
        return cell
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        let image = UIImage(named: imageNames[indexPath.row])
        
        // code to create resized image from https://www.snip2code.com/Snippet/89236/Resize-Image-in-iOS-Swift
        //create new size
        let scale:CGFloat = 5
        let newSize = CGSize(width: (image?.size.width)!/scale, height:
            (image?.size.height)!/scale)
        return newSize
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, insetForSectionAt section: Int) -> UIEdgeInsets {
        let sectionInsets = UIEdgeInsets(top: 25.0, left: 10.0, bottom: 25.0, right: 10.0)
        return sectionInsets
    }
    
    override func collectionView(_ collectionView: UICollectionView, viewForSupplementaryElementOfKind kind: String, at indexPath: IndexPath) -> UICollectionReusableView {
        var view: UICollectionReusableView?
        if kind == UICollectionView.elementKindSectionHeader {
            let header =
                collectionView.dequeueReusableSupplementaryView(ofKind: kind, withReuseIdentifier: "header", for: indexPath) as? HeaderCollectionReusableView
            header?.headerLabel.text = "this ting i made"
            view = header
        }
        else if kind == UICollectionView.elementKindSectionFooter {
            let footer = collectionView.dequeueReusableSupplementaryView(ofKind: kind, withReuseIdentifier: "footer", for: indexPath) as? FooterCollectionReusableView
            footer?.footerLabel.text = "this is the footer"
            view = footer
        }
        return view!
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "showDetail" {
            let indexPath = collectionView.indexPath(for: sender as! CollectionViewCell)
            let detailVC = segue.destination as! detailViewController
            detailVC.imageName = imageNames[(indexPath?.row)!]
        }
    }

}
