import React from 'react';

export default function Enquire(props) {
    return (
        <div className="container">
            <div className="outer-col">
                <div className="heading">Quick Enquiry</div>
                <div className="form-col">
                    <form action="#" method="post">
                        <input type="text" className="form-control" placeholder="Name" name="Name" id="user-name" required="" />
                        <input type="email" className="form-control" placeholder="Email" name="Name" id="Email-id" required="" />
                        <input type="text" className="form-control" placeholder="phone number" name="Name" id="phone-number" required="" />
                        <textarea placeholder="your message" className="form-control"></textarea>
                        <input type="submit" value="send" className="btn_apt" />
                    </form>
                </div>
            </div>
        </div>
    );
}