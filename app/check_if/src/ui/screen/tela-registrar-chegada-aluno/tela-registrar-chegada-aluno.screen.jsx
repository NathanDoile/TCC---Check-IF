import './tela-registrar-chegada-aluno.screen.css';
import Webcam from 'react-webcam';
import { useRef, useState, useCallback } from 'react';

export function TelaRegistrarChegadaAluno(){

    const webcamRef = useRef(null);

    const [imgSrc, setImgSrc] = useState(null);

    const capture = useCallback(() => {
        const imageSrc = webcamRef.current.getScreenshot();
        setImgSrc(imageSrc);
    }, [webcamRef]);

    return(
        <>
            <div>
                
                { imgSrc ? 
                    <img src={imgSrc} alt='webcam' /> : 
                    <Webcam height={600} width={600} ref={webcamRef} />
                }

                <button onClick={capture}>Capture photo</button>

            </div>
        </>
    )
}