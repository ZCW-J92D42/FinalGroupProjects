import React from 'react';
import './AboutUsPage.css';
import userPic from '../user.png';
import jia from '../profiles/hyunji.png'
import tam from '../profiles/tam.png'
import christy from '../profiles/christy.png'

function AboutUsPage (){


    return (
        <div className="about-section">
            <h1>About Us</h1>
            <h2>Thank you for your time. Hope you enjoy ZipDocs!</h2>
            <h2>We would love to answer any questions.</h2>

            <h2 style={{ textAlign: 'center' }}>Our Team</h2>

            <div className="row">
                <div className="column">
                    <div className="card">
                        <img src={tam} alt="Tam" style={{ width: '100%' }} />
                        <div className="container">
                            <h2>Tam Maria Doan</h2>
                            <p className="title">Software Developer</p>
                            <p>B.S in Computer Science</p>
                            <p>Former Passenger Service Agent with Sun Country Airlines</p>
                            <p>mdtam147@gmail.com</p>
                            <p>
                                <a href='https://github.com/TamMDoan'
                                   target="_blank"
                                   rel="noopener noreferrer">

                                    <button className="button">GitHub</button>
                                </a>
                            </p>
                            <p>
                                <a href='https://www.linkedin.com/in/tammariadoan/'
                                   target="_blank"
                                   rel="noopener noreferrer">

                                    <button className="button">LinkedIn</button>
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
                            <p>
                                <a href='https://github.com/Christy-ED'
                                   target="_blank"
                                   rel="noopener noreferrer">

                                    <button className="button">GitHub</button>
                                </a>
                            </p>
                            <p>
                                <a href='https://www.linkedin.com/in/christianne-edgard/'
                                   target="_blank"
                                   rel="noopener noreferrer">

                                    <button className="button">LinkedIn</button>
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
                            <p>B.A in Psychology</p>
                            <p>Former Emergency Medical Scribe & Assistant Manager </p>
                            <p>hyjialee@gmail.com</p>
                            <p>
                                <a href='https://github.com/hyunjialee/'
                                   target="_blank"
                                   rel="noopener noreferrer">

                                    <button className="button">GitHub</button>
                                </a>
                            </p>
                            <p>
                                <a href='https://www.linkedin.com/in/hyunjialee/'
                                   target="_blank"
                                   rel="noopener noreferrer">

                                    <button className="button">LinkedIn</button>
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
