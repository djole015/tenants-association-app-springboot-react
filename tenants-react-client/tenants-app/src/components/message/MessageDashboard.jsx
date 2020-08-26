import React, { Component } from "react";
import ApiMessageService from "../../services/ApiMessageService.js";

import moment from "moment";

class MessageDashboard extends Component {
  constructor(props) {
    super(props);

    this.state = {
      messages: [],
      mess: "",
    };

    this.deleteMessageClicked = this.deleteMessageClicked.bind(this);
    this.updateMessageClicked = this.updateMessageClicked.bind(this);
    this.refreshMessages = this.refreshMessages.bind(this);
  }

  componentDidMount() {
    this.refreshMessages();
  }

  refreshMessages() {
    ApiMessageService.getAllMessages().then((response) => {
      this.setState({ messages: response.data });
    });
  }

  deleteMessageClicked(id) {
    //console.log(id);
    ApiMessageService.deleteMessage(id).then((response) => {
      this.setState({ mess: `message with id = ${id} deleted` });
      this.refreshMessages();
    });
  }

  updateMessageClicked(id) {
    // redirecting
    console.log(id);
    this.props.history.push(`/messages/${id}`);
  }

  render() {
    return (
      <div>
        <div>
          <h2 className="container">Messages</h2>
          <table className="table table-bordered table-striped">
            <thead className="thead-dark">
              <tr>
                <th>Id</th>
                <th>Title</th>
                <th>Type</th>
                <th>Description</th>
                <th>Percentage Required</th>
                <th>Created At</th>
                <th>Flat Address</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {this.state.messages.map((message) => (
                <tr key={message.id}>
                  <td>{message.id}</td>
                  <td>{message.title}</td>
                  <td>{message.type}</td>
                  <td>{message.description}</td>
                  <td>{message.percentageRequired}</td>
                  <td>{moment(message.createdAt).format("YYYY-MM-DD")}</td>
                  <td>{message.flatAddress}</td>
                  <td>
                    <button
                      className="btn btn-warning"
                      onClick={() => this.updateMessageClicked(message.id)}
                    >
                      Edit
                    </button>{" "}
                    <button
                      className="btn btn-danger"
                      onClick={() => this.deleteMessageClicked(message.id)}
                    >
                      Delete
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
          <div className="row">
            <button
              className="btn btn-success"
              onClick={() => this.addMessageClicked()}
            >
              Create Message
            </button>
          </div>
        </div>
      </div>
    );
  }
}

export default MessageDashboard;
