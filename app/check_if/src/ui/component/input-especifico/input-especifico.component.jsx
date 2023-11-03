import './input-especifico.component.css';

export function InputEspecifico({imagem, alt, type, name, placeholder, isRequired, handleChange}) {

    return (
        <>
            <div className="input-login">
                <img src={imagem} alt={alt} className="imagem-input" />
                <input
                    type={type}
                    name={name}
                    placeholder={placeholder}
                    className="texto-input"
                    required={isRequired}
                    onChange={handleChange}
                />
            </div>
        </>
    )
}