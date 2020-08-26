import React, { Component } from "react";
import { Link } from "react-router-dom";

class Header extends Component {
  render() {
    return (
      <div className="container" style={{ marginTop: "75px" }}>
        <header>
          <nav
            className="navbar navbar-expand fixed-top navbar-dark"
            style={{ backgroundColor: "#232624" }}
          >
            <Link to="/" className="navbar-brand">
              Tenants Association
            </Link>

            <ul className="navbar-nav ml-auto">
              <li className="nav-item ">
                <Link to="/messages" className="navbar-brand">
                  Messages
                </Link>
              </li>
              <li className="nav-item">
                <Link to="/flats" className="navbar-brand">
                  Flats
                </Link>
              </li>
            </ul>
          </nav>
        </header>
      </div>
    );
  }
}

export default Header;
