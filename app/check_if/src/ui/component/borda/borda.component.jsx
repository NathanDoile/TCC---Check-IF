import './borda.component.css';
import bordaSuperior from '../../../assets/images/Borda.svg';
import bordaInferior from '../../../assets/images/Borda-inferior.png';

export function Borda({posicao}){
    console.log(posicao);
    return(
        <>
            {posicao === 'superior' ? 
            <img src={bordaSuperior} className = 'borda-superior'/> : 
            <img src={bordaInferior} className = 'borda-inferior'/>}
        </>
    )
}