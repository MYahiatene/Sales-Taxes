<template>
    <v-app id="inspire">
        <v-system-bar app>
            <v-spacer></v-spacer>

        </v-system-bar>

        <v-app-bar app>

            <v-spacer></v-spacer>
            <v-toolbar-title>Sales Taxes Application</v-toolbar-title>
            <v-spacer></v-spacer>
        </v-app-bar>
        <v-navigation-drawer
            fixed
            temporary
        >
        </v-navigation-drawer>

        <v-main class="grey lighten-1">
            <v-container>
                <v-row>
                    <template>
                        <v-col>
                            <v-card
                                class="mx-auto"
                                color="grey lighten-2"
                                max-width="800"
                            >

                                <v-list-item three-line>
                                    <v-list-item-content>
                                        <v-list-item-title class="text-h3 black--text mb-1">
                                            Shopping Basket
                                        </v-list-item-title>

                                    </v-list-item-content>
                                </v-list-item>
                                <v-list-item v-for="item in items" :key="item">

                                    <v-list-item-content>
                                        <div class="text-h5 black--text mb-1">
                                            {{ item }}
                                        </div>

                                    </v-list-item-content>


                                </v-list-item>
                                <v-card-actions>
                                    <v-container fluid>
                                        <v-form ref="form" v-model="valid">
                                            <v-text-field id="textField"
                                                          v-model="input"
                                                          :rules="inputRules"
                                                          class="text-h7 black--text mb-1"

                                            ></v-text-field>
                                        </v-form>
                                    </v-container>
                                    <v-btn
                                        outlined
                                        rounded
                                        text
                                        class="blue-grey"

                                        @click="addItem()"
                                    >
                                        Add item to basket
                                    </v-btn>
                                </v-card-actions>
                            </v-card>
                        </v-col>

                    </template>
                </v-row>
            </v-container>
            <div>
                <v-snackbar
                    v-model="snackbar"
                    :timeout="timeout"
                >
                    {{ text }}

                    <template #action="{ attrs }">
                        <v-btn
                            color="blue"
                            text
                            v-bind="attrs"
                            @click="snackbar = false"
                        >
                            Close
                        </v-btn>
                    </template>
                </v-snackbar>
            </div>
        </v-main>
    </v-app>
</template>

<script>
export default {
    data: () => ({
        items: [],
        input: "",
        valid: false,
        snackbar: false,
        timeout: 1500,
        text: "Error! Wrong Input!",
        inputRules: [v => v.length > 0, v => v.split(" ").length >= 3, v => Number.isInteger(Number(v.split(" ")[0]))

        ],

    }),
    methods: {
        addItem() {
            if (this.valid) {
                this.items.push(this.input.replace(" at ", ": "));
            } else {
                this.snackbar = true;
            }
        }
        ,
    }
    ,
}
</script>
<style>
#textField {
    color: black !important;

}

</style>
