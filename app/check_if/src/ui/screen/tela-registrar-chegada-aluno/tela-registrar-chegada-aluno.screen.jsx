import './tela-registrar-chegada-aluno.screen.css';
import { Borda, LogoInicial, Botao } from '../../component';
import cameraIcone from '../../../assets/images/Camera-branco.svg';
import { toast } from 'react-toastify';
import Webcam from 'react-webcam';
import { useRef, useCallback, useState } from 'react';
import { useLerCracha } from '../../../hooks';

export function TelaRegistrarChegadaAluno(){

    const {lerCracha} = useLerCracha();

    const [formInput, setFormInput] = useState({
        professor: 0,
        disciplina: "",
        motivo: "",
        matricula: ""
    });

    const [outroMotivo, setOutroMotivo] = useState(false);

    const [capturarCodigoBarras, setCapturarCodigoBarras] = useState(false);

    const webcamRef = useRef(null);

    const [imgSrc, setImgSrc] = useState(null);

    function handleChange(event) {
        const { name, value } = event.target;

        if(name === "motivo" && value === "outro"){
            
            setFormInput((oldFormInput) => ({ ...oldFormInput, [name]: "" }));

            setOutroMotivo(true);
        }
        else if(name === "outroMotivo"){

            setFormInput((oldFormInput) => ({ ...oldFormInput, "motivo": value }));
        }
        else if(name === "motivo" && value !== "outro" && outroMotivo){
            setFormInput((oldFormInput) => ({ ...oldFormInput, [name]: value }));

            setOutroMotivo(false);
        }
        else{
            setFormInput((oldFormInput) => ({ ...oldFormInput, [name]: value }));
        }
        
    }

    async function registrarChegada(event){
        event.preventDefault();

        if(formInput.professor === 0 || formInput.motivo === "" || formInput.matricula === ""){
            toast.error("Preencha todos os campos obrigatórios!");
        }
        else{
            toast.success("OK!");
        }
    }

    const capture = useCallback(() => {
        const imageSrc = webcamRef.current.getScreenshot();
        setImgSrc(imageSrc);
    }, [webcamRef]);

    async function concluirLerCracha(){

        try{
            const response = await lerCracha(imgSrc);
            console.log(response)
            setCapturarCodigoBarras(false);

            setFormInput((oldFormInput) => ({ ...oldFormInput, "matricula": response.matricula }));
        }
        catch(error){
            setImgSrc(null);
        }
    }

    return (
        <section className="section-registrar-chegada">

            <Borda posicao="superior"/>

            <main className="main-registrar-chegada">

                {!capturarCodigoBarras 
                ? 
                <>
                    <LogoInicial />

                    <h1 className = 'titulo-registrar-chegada'>Registre sua chegada atrasada</h1>

                    <span className='container-botao-camera'>
                        <Botao cor="verde" onClick={() => {setCapturarCodigoBarras(true); setImgSrc(null);}}>
                            <img className="icone-camera" src={cameraIcone} alt="câmera" />
                            <p className="legenda-botao-camera">Ler crachá</p>
                        </Botao>
                        <p>*</p>
                    </span>

                    <form className="form-registrar-chegada-atrasada" onSubmit={registrarChegada}>

                        <span className="form-span-inputs">

                            <label className="label-input">
                                <span>Professor:<span className='input-obrigatorio'>*</span></span>
                                <select name="professor" className="caixa-input" onChange={handleChange}>
                                    <option value={0}>Selecione</option>
                                    <option value={1}>Roberto</option>
                                    <option value={2}>Lourenço</option>
                                </select>
                            </label>

                            <label className="label-input">
                                <span>Disciplina:</span>
                                <input type="text" name="disciplina" className="caixa-input" onChange={handleChange} />
                            </label>

                        </span>

                        <span className="form-span-inputs">

                            <label className="label-input">
                                <span>Motivo:<span className='input-obrigatorio'>*</span></span>
                                <select name="motivo" className="caixa-input" onChange={handleChange}>
                                    <option value="">Selecione</option>
                                    <option value="Despertador não tocou">Despertador não tocou</option>
                                    <option value="Problema no trânsito">Problema no trânsito</option>
                                    <option value="outro">Outro</option>
                                </select>
                            </label>

                            {outroMotivo 
                            ? 
                                <label className="label-input">
                                    <span>Outro:<span className='input-obrigatorio'>*</span></span>
                                    <input type='text' name='outroMotivo' className="caixa-input" onChange={handleChange} />
                                </label>
                            : null}

                        </span>

                        <Botao cor="amarelo">Registrar</Botao>

                    </form>
                </>    
                : null}


            </main>
            
            {capturarCodigoBarras 
            ? 
                <div className="modal-camera">
                    
                    { imgSrc ? 
                        <img src={imgSrc} alt='webcam' /> : 
                        <Webcam height={600} width={600} ref={webcamRef} />
                    }

                    {!imgSrc 
                    ? 
                        <Botao cor="verde" onClick={capture}>Capturar foto</Botao>
                    : 
                    <>
                        <Botao cor="verde" onClick={() => {setImgSrc(null)}}>Tentar novamente</Botao>
                        <Botao cor="amarelo" onClick={concluirLerCracha}>Concluir</Botao>
                    </>
                    }
                    

                </div>
            : null}

            <Borda posicao="inferior" />

        </section>
    )
}