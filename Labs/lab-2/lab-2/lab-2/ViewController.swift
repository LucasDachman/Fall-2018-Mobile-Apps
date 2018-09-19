//
//  ViewController.swift
//  lab-2
//
//  Created by Lucas Dachman on 9/19/18.
//  Copyright © 2018 Lucas Dachman. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    @IBOutlet weak var contentControl: UISegmentedControl!
    @IBOutlet weak var capsSwitch: UISwitch!
    @IBOutlet weak var colorControl: UISegmentedControl!
    @IBOutlet weak var sizeSlider: UISlider!
    @IBOutlet weak var textView: UITextView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        sizeSlider.minimumValue = 12
        sizeSlider.maximumValue = 72
        sizeSlider.setValue(14, animated: false)
        
        setContent()
    }

    func setContent() {
        // set content
        switch contentControl.titleForSegment(at: contentControl.selectedSegmentIndex) {
        case "Word":
            textView.isHidden = false
            textView.text = "Lorem"
        case "Paragraph":
            textView.isHidden = false
            textView.text = longLorem
        // add image option
        case "Image":
            textView.isHidden = true
        default:
            textView.text = textView.text
        }
        
        // set caps
        if capsSwitch.isOn {
            textView.text = textView.text.uppercased()
        } else {
            textView.text = textView.text.lowercased()
        }
        
        // set color
        switch colorControl.titleForSegment(at: colorControl.selectedSegmentIndex) {
        case "Red":
            textView.textColor = UIColor.red
        case "Green":
            textView.textColor = UIColor.green
        case "Blue":
            textView.textColor = UIColor.blue
        default:
            textView.textColor = UIColor.black
        }
        
        // set size
        textView.font = textView.font?.withSize(CGFloat(sizeSlider.value))
    }
    
    @IBAction func onContentChange(_ sender: UISegmentedControl) {
        setContent()
    }
    @IBAction func onCapsChange(_ sender: UISwitch) {
        setContent()
    }
    @IBAction func onColorChange(_ sender: UISegmentedControl) {
        setContent()
    }
    @IBAction func onSizeChange(_ sender: UISlider) {
        setContent()
    }
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    let longLorem = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Idem fecisset Epicurus, si sententiam hanc, quae nunc Hieronymi est, coniunxisset cum Aristippi vetere sententia. Superiores tres erant, quae esse possent, quarum est una sola defensa, eaque vehementer. Duo Reges: constructio interrete. Ita enim se Athenis collocavit, ut sit paene unus ex Atticis, ut id etiam cognomen videatur habiturus. Virtutis, magnitudinis animi, patientiae, fortitudinis fomentis dolor mitigari solet. Nosti, credo, illud: Nemo pius est, qui pietatem-; An vero displicuit ea, quae tributa est animi virtutibus tanta praestantia? Omnibus enim artibus volumus attributam esse eam, quae communis appellatur prudentia, quam omnes, qui cuique artificio praesunt, debent habere. Me ipsum esse dicerem, inquam, nisi mihi viderer habere bene cognitam voluptatem et satis firme conceptam animo atque comprehensam. Aristoteles, Xenocrates, tota illa familia non dabit, quippe qui valitudinem, vires, divitias, gloriam, multa alia bona esse dicant, laudabilia non dicant.Solum praeterea formosum, solum liberum, solum civem, stultost; Audax negotium, dicerem impudens, nisi hoc institutum postea translatum ad philosophos nostros esset. Magna laus. Quod enim dissolutum sit, id esse sine sensu, quod autem sine sensu sit, id nihil ad nos pertinere omnino. Haec quo modo conveniant, non sane intellego. Et quidem, Cato, hanc totam copiam iam Lucullo nostro notam esse oportebit; Cenasti in vita numquam bene, cum omnia in ista Consumis squilla atque acupensere cum decimano.Cur igitur, inquam, res tam dissimiles eodem nomine appellas? At enim, qua in vita est aliquid mali, ea beata esse non potest. Hic ego: Etsi facit hic quidem, inquam, Piso, ut vides, ea, quae praecipis, tamen mihi grata hortatio tua est. Possumusne ergo in vita summum bonum dicere, cum id ne in cena quidem posse videamur? Aliis esse maiora, illud dubium, ad id, quod summum bonum dicitis, ecquaenam possit fieri accessio. Oculorum, inquit Plato, est in nobis sensus acerrimus, quibus sapientiam non cernimus. Sed quia studebat laudi et dignitati, multum in virtute processerat. Haec non erant eius, qui innumerabilis mundos infinitasque regiones, quarum nulla esset ora, nulla extremitas, mente peragravisset. Quod etsi ingeniis magnis praediti quidam dicendi copiam sine ratione consequuntur, ars tamen est dux certior quam natura. Quae cum ita sint, effectum est nihil esse malum, quod turpe non sit.Ex quo illud efficitur, qui bene cenent omnis libenter cenare, qui libenter, non continuo bene. Aliter enim explicari, quod quaeritur, non potest. Verba tu fingas et ea dicas, quae non sentias? Quae cum praeponunt, ut sit aliqua rerum selectio, naturam videntur sequi; Aristoteles, Xenocrates, tota illa familia non dabit, quippe qui valitudinem, vires, divitias, gloriam, multa alia bona esse dicant, laudabilia non dicant. Negat esse eam, inquit, propter se expetendam. Aut unde est hoc contritum vetustate proverbium: quicum in tenebris? Huic verbo omnes, qui ubique sunt, qui Latine sciunt, duas res subiciunt, laetitiam in animo, commotionem suavem iucunditatis in corpore."
}

