import './botao.component.css';

export function Botao({cor, onClick, children}){
    return(
        <>
            {cor === "verde" 
            ? <button className = 'botao verde' onClick={onClick}>{children}</button>
            : <button className = 'botao amarelo' onClick={onClick}>{children}</button>}
        </>
    )
}