import React from 'react';

export default function Enquire(props) {
    return (
        <div class="container">
            <div class="outer-col">
                <div class="heading">Quick Enquiry</div>
                <div class="form-col">
                    <form action="#" method="post">
                        <input type="text" class="form-control" placeholder="Name" name="Name" id="user-name" required="" />
                        <input type="email" class="form-control" placeholder="Email" name="Name" id="Email-id" required="" />
                        <input type="text" class="form-control" placeholder="phone number" name="Name" id="phone-number" required="" />
                        <textarea placeholder="your message" class="form-control"></textarea>
                        <input type="submit" value="send" class="btn_apt" />
                    </form>
                </div>
            </div>
        </div>
    );
}