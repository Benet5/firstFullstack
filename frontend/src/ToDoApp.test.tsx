
import {render, screen, waitFor} from '@testing-library/react';
import ToDoApp from "./ToDoApp";
import {MemoryRouter} from "react-router-dom";
import {useState} from "react";

beforeAll(()=>localStorage.setItem('jwt', '1234'))

test("list should been rendered", async () => {
    jest.spyOn(global, 'fetch').mockImplementation(() => {
            return Promise.resolve({
                ok: true,
                json: () => Promise.resolve(
                     [{name: "Han Solo",
                        description: "Star Wars schauen",
                         status: false,
                        id: "12345g",
                         formattedEndDate: "24 03 2022"
                    }, {
                        formattedEndDate: "26 03 2022",
                        name: "Chui",
                        description: "Star Wars Idol",
                        id: "1234537d",
                        status: false
                    }]
                )
            } as Response);
        });


        render(<ToDoApp/>, {wrapper: MemoryRouter})
        await waitFor(() => {
            expect(screen.getAllByTestId("items").length).toEqual(2);
        });
    }
)
/*

test("delete checked items", async () => {
        jest.spyOn(global, 'fetch').mockImplementation((URL :string) => {
            if (URL === "http://localhost:8080/todoapp/getallitems")
                return Promise.resolve({
                    ok: true,
                    json: () => Promise.resolve(
                        [{name: "Han Solo",
                            description: "Star Wars schauen",
                            status: true,
                            id: "12345g",
                            formattedEndDate: "24 03 2022"
                        }, {
                            formattedEndDate: "26 03 2022",
                            name: "Chui",
                            description: "Star Wars Idol",
                            id: "1234537d",
                            status: false
                        }]
                    )
                } as Response);
            else {
            expect(URL).toEqual("http://localhost:8080/todoapp/checkeditems")
            return Promise.resolve({
                ok: false,
            } as Response);
        }});

        render(<ToDoApp/>, {wrapper: MemoryRouter})
    const Button = screen.getByTestId("deleteCheckedbuttontest") as HTMLButtonElement;
    Button.click();

    await waitFor(() => {
        expect(screen.getByTestId("errorItemApp").textContent).toEqual("" +
            "")
    });
    }
) */
