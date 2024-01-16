import React from 'react';
import './AboutUsPage.css';
import userPic from '../user.png';
import jia from '../profiles/hyunji.png'
import tam from '../profiles/tam.png'
import christy from '../profiles/christy.png'
import jiaqr from '../profiles/jiaQR.JPG'
import tamqr from '../profiles/tamqr.png'
import christyqr from '../profiles/christyQR.png'


function AboutUsPage (){


    return (
        <div className="about-section">
            <h1 style={{fontSize: '80px', marginTop: '-15px'}}>About Us</h1>
            <h2>Thank you for your time. Hope you enjoy ZipDocs!</h2>
            <h2>We would love to answer any questions.</h2>

            <h1 style={{textAlign: 'center'}}>Our Team</h1>
            <h2 style={{marginBottom: '-30px'}}>We would love to connect through our LinkedIn QR Codes.</h2>

            <div className="row">
                <div className="column">
                    <div className="card">
                        <img src={tam} alt="Tam" style={{width: '100%'}}/>
                        <div className="container">
                            <h2>Tam Maria Doan</h2>
                            <p className="title">Software Developer</p>
                            <p>B.S. in Computer Science</p>
                            <p>Former Passenger Service Agent with Sun Country Airlines</p>
                            <p>mdtam147@gmail.com</p>
                            <img src={tamqr} alt="TamQR" style={{width: '80%'}}/>

                            <p>
                                <a href='https://github.com/TamMDoan'
                                   target="_blank"
                                   rel="noopener noreferrer">

                                    <button className="button">GitHub</button>
                                </a>
                            </p>
                        </div>
                    </div>
                </div>

                <div className="column">
                    <div className="card">
                        <img src={christy} alt="Christy" style={{width: '100%'}}/>
                        <div className="container">
                            <h2>Christianne (Christy) Edgard</h2>
                            <p className="title">Software Developer</p>
                            <p>US Army Veteran</p>
                            <p>Serving in the PA Army National Guard</p>
                            <p>Edgardchristianne@gmail.com</p>
                            <img src={christyqr} alt="christyqr" style={{width: '80%'}}/>

                            <p>
                                <a href='https://github.com/Christy-ED'
                                   target="_blank"
                                   rel="noopener noreferrer">

                                    <button className="button">GitHub</button>
                                </a>
                            </p>

                        </div>
                    </div>
                </div>

                <div className="column">
                    <div className="card">
                        <img src={jia} alt="Jia" style={{width: '100%'}}/>
                        <div className="container">
                            <h2>Hyunji (Jia) Lee</h2>
                            <p className="title">Software Developer</p>
                            <p>B.A. in Psychology</p>
                            <p>Former Emergency Medical Scribe & Assistant Manager </p>
                            <p>hyjialee@gmail.com</p>
                            <img src={jiaqr} alt="JiaQR" style={{width: '80%'}}/>
                            <p>
                                <a href='https://github.com/hyunjialee/'
                                   target="_blank"
                                   rel="noopener noreferrer">

                                    <button className="button">GitHub</button>
                                </a>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default AboutUsPage;
