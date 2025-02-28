import React, { useEffect, useState } from 'react';
import { CampaignApi } from "../api/CampaignApi";
import { CampaignResponse } from "../model/api/CampaignResponse";
import { CampaignRequest } from "../model/api/CampaignRequest";
import { Town } from "../model/api/Town";
import { Container, CampaignListContainer, CampaignListItem, Form, Input, Select, Button } from "../App.styles";

interface CampaignListProps {
    productId: number;
}

export const CampaignList: React.FC<CampaignListProps> = ({ productId }) => {
    const [campaign, setCampaign] = useState<CampaignResponse | null>(null);
    const [formVisible, setFormVisible] = useState(false);
    const [newCampaign, setNewCampaign] = useState<CampaignRequest>({
        name: '',
        keywords: '',
        bidAmount: 0,
        campaignFund: 0,
        status: true,
        town: Town.NEW_YORK,
        radius: 0,
        productId
    });

    useEffect(() => {
        const fetchCampaign = async () => {
            try {
                const response = await CampaignApi.getCampaigns(productId);
                if (response.data.length > 0) {
                    setCampaign(response.data[0]);
                }
            } catch (error) {
                console.error(error);
            }
        };
        fetchCampaign();
    }, [productId]);

    const addCampaign = async () => {
        if (campaign || newCampaign.name.trim() === '' || newCampaign.keywords.trim() === '') return;

        try {
            const response = await CampaignApi.addCampaign(newCampaign);
            setCampaign({ ...newCampaign, id: response.data.id });
            setFormVisible(false);
        } catch (error) {
            console.error(error);
        }
    };

    const deleteCampaign = async (campaignId: number) => {
        try {
            await CampaignApi.deleteCampaign(campaignId);
            setCampaign(null);
        } catch (error) {
            console.error(error);
        }
    };

    const editCampaign = () => {
        if (!campaign) return;
        setNewCampaign({ ...campaign, productId });
        setFormVisible(true);
    };

    const updateCampaign = async () => {
        if (!campaign) return;
        try {
            console.log(newCampaign)
            await CampaignApi.updateCampaign(campaign.id, newCampaign);
            setCampaign({ ...campaign, ...newCampaign });
            setFormVisible(false);
        } catch (error) {
            console.error(error);
        }
    };

    return (
        <Container>
            <h4>Campaign</h4>
            {campaign ? (
                <CampaignListContainer>
                    <CampaignListItem>
                        <div>
                            <span>{campaign.name}</span>
                            <div><strong>Keywords:</strong> {campaign.keywords}</div>
                            <div><strong>Bid Amount:</strong> {campaign.bidAmount}</div>
                            <div><strong>Campaign Fund:</strong> {campaign.campaignFund}</div>
                            <div><strong>Town:</strong> {campaign.town}</div>
                            <div><strong>Radius:</strong> {campaign.radius}</div>
                            <Button onClick={editCampaign}>Edit</Button>
                            <Button onClick={() => deleteCampaign(campaign.id)}>Delete</Button>
                        </div>
                    </CampaignListItem>
                </CampaignListContainer>
            ) : (
                <>
                    <p>No campaign available</p>
                    {!formVisible && <Button onClick={() => setFormVisible(true)}>Add Campaign</Button>}
                </>
            )}

            {formVisible && (
                <Form>
                    <h5>{campaign ? "Edit Campaign" : "Add New Campaign"}</h5>
                    <Input type="text" placeholder="Enter campaign name" value={newCampaign.name} onChange={(e) => setNewCampaign({ ...newCampaign, name: e.target.value })} />
                    <Input type="text" placeholder="Enter keywords" value={newCampaign.keywords} onChange={(e) => setNewCampaign({ ...newCampaign, keywords: e.target.value })} />
                    <Input type="number" placeholder="Bid Amount" value={newCampaign.bidAmount} onChange={(e) => setNewCampaign({ ...newCampaign, bidAmount: Number(e.target.value) })} />
                    <Input type="number" placeholder="Campaign Fund" value={newCampaign.campaignFund} onChange={(e) => setNewCampaign({ ...newCampaign, campaignFund: Number(e.target.value) })} />
                    <Select value={newCampaign.town} onChange={(e) => setNewCampaign({ ...newCampaign, town: e.target.value as unknown as Town })}>
                        {Object.values(Town).filter(value => typeof value === 'string').map((townName) => (
                            <option key={townName} value={townName}>{(townName as string).replace('_', ' ').toUpperCase()}</option>
                        ))}
                    </Select>
                    <Input type="number" placeholder="Radius" value={newCampaign.radius} onChange={(e) => setNewCampaign({ ...newCampaign, radius: Number(e.target.value) })} />
                    {campaign ? (
                        <Button onClick={updateCampaign}>Update Campaign</Button>
                    ) : (
                        <Button onClick={addCampaign}>Add Campaign</Button>
                    )}
                    <Button onClick={() => setFormVisible(false)}>Cancel</Button>
                </Form>
            )}
        </Container>
    );
};

export default CampaignList;
